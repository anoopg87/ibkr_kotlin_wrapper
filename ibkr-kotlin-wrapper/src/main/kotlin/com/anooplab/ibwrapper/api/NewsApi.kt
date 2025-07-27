package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.ib.client.*

 /**
 * News API for requesting news providers.
 */
class NewsApi(
    private val client: IbClient,
    private val callbacks: CallBacks
) {
    /**
     * Request news providers, with optional callback for news providers.
     */
    fun reqNewsProviders(
        onNewsProviders: ((newsProviders: Array<out NewsProvider>?) -> Unit)? = null
    ) {
        onNewsProviders?.let { callbacks.onNewsProviders = it }
        client.reqNewsProviders()
    }

    /**
     * Request news article, with optional callback for news article.
     */
    fun reqNewsArticle(
        requestId: Int,
        providerCode: String?,
        articleId: String?,
        newsArticleOptions: MutableList<TagValue>?,
        onNewsArticle: ((requestId: Int, articleType: Int, articleText: String?) -> Unit)? = null
    ) {
        onNewsArticle?.let { callbacks.onNewsArticle = it }
        client.reqNewsArticle(requestId, providerCode, articleId, newsArticleOptions)
    }
}
