package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.ib.client.NewsProvider
import com.ib.client.TagValue
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NewsApiTest {
    private lateinit var client: IbClient
    private lateinit var callbacks: CallBacks
    private lateinit var api: NewsApi

    @BeforeEach
    fun setup() {
        client = mockk(relaxed = true)
        callbacks = CallBacks()
        api = NewsApi(client, callbacks)
    }

    @Test
    fun `reqNewsProviders sets callback and calls client`() {
        val onNewsProviders = mockk<(Array<out NewsProvider>?) -> Unit>(relaxed = true)
        api.reqNewsProviders(onNewsProviders)
        assertEquals(onNewsProviders, callbacks.onNewsProviders)
        verify { client.reqNewsProviders() }
    }

    @Test
    fun `reqNewsArticle sets callback and calls client`() {
        val onNewsArticle = mockk<(Int, Int, String?) -> Unit>(relaxed = true)
        val options = mutableListOf<TagValue>()
        api.reqNewsArticle(1, "provider", "articleId", options, onNewsArticle)
        assertEquals(onNewsArticle, callbacks.onNewsArticle)
        verify { client.reqNewsArticle(1, "provider", "articleId", options) }
    }
}
