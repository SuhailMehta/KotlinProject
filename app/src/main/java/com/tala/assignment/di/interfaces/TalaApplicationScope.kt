package com.tala.assignment.di.interfaces

import javax.inject.Scope


@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class TalaApplicationScope