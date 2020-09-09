package com.skfo763.data_converter_processor.xls

import com.google.auto.service.AutoService
import com.skfo763.data_converter_annotation.XlsColumnInfo
import com.skfo763.data_converter_annotation.XlsConvertResult
import com.skfo763.data_converter_annotation.XlsEntity
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.asTypeName
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Name
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
class XlsProcessor : AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"

        val fileBuilder = FileSpec.builder(
            "com.skfo763.xlsconvert.generated",
            "XlsConvertExtension"
        )
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(
            XlsEntity::class.java.name,
            XlsColumnInfo::class.java.name
        )
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        val classElements = roundEnv.getElementsAnnotatedWith(XlsEntity::class.java)
        if(!checkElementType(ElementKind.CLASS, classElements)) return false
        classElements.forEach {
            fileBuilder.addFunction(convertKeyToColumnList(it))
        }

        fileBuilder.addImport(XlsConvertResult::class.java, "")
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: return false
        fileBuilder.build().writeTo(File(kaptKotlinGeneratedDir))
        return true
    }

    private fun convertKeyToColumnList(element: Element): FunSpec {
        val convertFunSpec = FunSpec.builder("getColumnList")
            .receiver(element.asType().asTypeName())
            .returns(XlsConvertResult::class.java)
            .addStatement("val result = %T()", XlsConvertResult::class.java)

        element.getAnnotation(XlsEntity::class.java)?.let { entity ->
            convertFunSpec.apply {
                addStatement("result.tableName = \"${entity.tableName}\"")
            }
        }

        val fieldElement = element.enclosedElements
        fieldElement.forEach { elem ->
            elem.getAnnotation(XlsColumnInfo::class.java)?.let {
                convertFunSpec.apply {
                    addStatement("result.columnList.add(" +
                            "Triple(\"${it.entityName}\", " +
                            "$elem, " +
                            "${elem.asType()}::class.java))"
                    )
                }
            }
        }
        return convertFunSpec.addStatement("return result").build()
    }

    private fun checkElementType(kind: ElementKind, elements: Set<Element>): Boolean {
        if (elements.isEmpty()) return false
        elements.forEach {
            if (it.kind != kind) {
                printMessage(
                    Diagnostic.Kind.ERROR, "Only ${kind.name} Are Supported", it
                )
                return false
            }
        }
        return true
    }

    private fun printMessage(kind: Diagnostic.Kind, message: String, element: Element) {
        processingEnv.messager.printMessage(kind, message, element)
    }

    private fun createFieldNameAndTag(fieldName: Name, tag: String): String {
        return "XlsConvertResult(\"$fieldName\", \"$tag\")"
    }
}