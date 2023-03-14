package com.example.sampleshop.helpers

import android.content.Context
import android.content.SharedPreferences
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.AdditionalMatchers
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

const val TEST_ID = "1"
@RunWith(MockitoJUnitRunner::class)
class HelperTest {
    private val context = mock(Context::class.java)
    private val sharedPreferences = mock(SharedPreferences::class.java)
    private val editor = mock(SharedPreferences.Editor::class.java)

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        `when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences)
        `when`(sharedPreferences.edit()).thenReturn(editor)
    }

    @Test
    fun isFavorite() {
        Helper.addId(context, TEST_ID)
        verify(editor).putString(eq("favorites"), contains(TEST_ID))
        verify(sharedPreferences).getString(eq("favorites"), anyString())
        Helper.isFavorite(context, TEST_ID)
        verify(sharedPreferences, times(2)).getString(eq("favorites"), anyString())
    }

    @Test
    fun addMovieId() {
        Helper.addId(context, TEST_ID)
        verify(editor).putString(eq("favorites"), contains(TEST_ID))
        verify(sharedPreferences).getString(eq("favorites"), anyString())
    }

    @Test
    fun removeMovieId() {
        Helper.addId(context, TEST_ID)
        verify(editor).putString(eq("favorites"), contains(TEST_ID))
        verify(sharedPreferences).getString(eq("favorites"), anyString())

        Helper.removeId(context, TEST_ID)
        verify(editor).putString(eq("favorites"), AdditionalMatchers.not(contains(TEST_ID)))
        verify(sharedPreferences, times(2)).getString(eq("favorites"), anyString())

    }
}