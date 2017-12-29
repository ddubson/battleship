package com.ddubson.battleship.cli

import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.io.ByteArrayOutputStream
import java.io.PrintStream

@RunWith(JUnitPlatform::class)
internal class StandardSystemCLIAdapterTest {
    private lateinit var spiedOnPrintStream: PrintStream

    @BeforeEach
    fun before() {
        spiedOnPrintStream = spy(PrintStream(ByteArrayOutputStream()))
        System.setOut(spiedOnPrintStream)
    }

    @AfterEach
    fun after() {
        System.setOut(null)
    }

    @Test
    fun `flush should call underlying System_out_flush`() {
        val sysCliAdapter = StandardSystemCLIAdapter()
        sysCliAdapter.flush()

        verify(spiedOnPrintStream).flush()
    }

    @Test
    fun `print should call underlying System_out_print`() {
        val sysCliAdapter = StandardSystemCLIAdapter()
        sysCliAdapter.print("message")

        verify(spiedOnPrintStream).print("message")
    }

    @Test
    fun `println should call underlying System_out_println`() {
        val sysCliAdapter = StandardSystemCLIAdapter()
        sysCliAdapter.println("message")

        verify(spiedOnPrintStream).println("message")
    }

    @Test
    fun `readLine should call underlying kotlin readLine`() {
        val inputStream = "data".byteInputStream()
        System.setIn(inputStream)
        try {
            val sysCliAdapter = StandardSystemCLIAdapter()
            val result = sysCliAdapter.readLine()
            assertThat(result, equalTo("data"))
        } finally {
            System.setIn(null)
        }
    }
}