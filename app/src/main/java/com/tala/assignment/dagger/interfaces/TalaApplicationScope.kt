package com.tala.assignment.dagger.interfaces

import javax.inject.Scope


@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class TalaApplicationScope