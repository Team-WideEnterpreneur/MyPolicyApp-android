package com.skfo763.my_data_app.extension

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    this.add(disposable)
}

operator fun CompositeDisposable.minusAssign(disposable: Disposable) {
    this.remove(disposable)
}