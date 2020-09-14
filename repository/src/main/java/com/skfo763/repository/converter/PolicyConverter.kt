package com.skfo763.repository.converter

import com.skfo763.remote.data.PolicyModel
import com.skfo763.repository.data.PolicyData

class PolicyConverter {
    
    fun convertModelToPolicyData(model: PolicyModel) = PolicyData(
        model.policyIdx,
        model.policyName,
        model.category,
        model.requireSchoolCredit,
        model.endDueDate,
        model.requireGrade,
        model.requireIncome,
        model.homeOwnerState,
        model.requireMaxAge,
        model.requireMinAge,
        model.requireFamilyStat,
        model.policyUrl,
        model.startDueDate,
        model.firstUserMid,
        model.secondUserMid,
        model.requireWorkDayMax,
        model.requireWorkDayMin,
        model.employmentState
    )
    
}