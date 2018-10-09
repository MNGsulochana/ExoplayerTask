package com.hackathlon.exoplayertask.injection.annotate

import javax.inject.Qualifier

@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
@Qualifier
annotation class ScopeContext
