package com.example.stock.base.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween

object AnimateScreen {
    fun enterTransition(): AnimatedContentTransitionScope<*>.() -> EnterTransition? = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(300)
        )
    }

    fun popEnterTransition(): AnimatedContentTransitionScope<*>.() -> EnterTransition? = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(300)
        )
    }

    fun exitTransition(): AnimatedContentTransitionScope<*>.() -> ExitTransition? = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(300)
        )
    }

    fun popExitTransition(): AnimatedContentTransitionScope<*>.() -> ExitTransition? = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(300)
        )
    }
}