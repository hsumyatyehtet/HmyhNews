package com.hmyh.news.framework

import com.hmyh.domain.ArticleListVO
import com.hmyh.domain.NewsListVO
import com.hmyh.domain.SourceVO


fun getNewList(): NewsListVO {
    return (
            NewsListVO(
                "ok", 14474,
                mutableListOf(
                    ArticleListVO(
                        SourceVO("engadget", "Engadget"),
                        "Kris Holt",
                        "New York passes a bill to limit bitcoin mining",
                        "https://www.engadget.com/new-york-cryptocurrency-bill-bitcoin-mining-climate-change-161126292.html",
                        "https://s.yimg.com/os/creatr-uploaded-images/2021-05/a8217250-bdfa-11eb-bfc4-2663225cea83",
                        "https://s.yimg.com/os/creatr-uploaded-images/2021-05/a8217250-bdfa-11eb-bfc4-2663225cea83",
                        "2022-06-03T16:11:26Z",
                        "New York lawmakers have passed a bill\r\n that would temporarily ban new bitcoin\r\n mining operations. Early on Friday, state senators voted 36-27 to pass the legislation. It's now bound for the desk of… [+2036 chars]"
                    ),
                    ArticleListVO(
                        SourceVO(null,"Gizmodo.com"),
                        "Passant Rabie",
                        "Jay-Z and Jack Dorsey Launch Bitcoin Academy for New Generation of Crypto Bros",
                        "Rapper and entrepreneur Shawn Carter, better known as Jay-Z, is bringing Bitcoin to the place where he grew up. On Thursday, Jay-Z and former Twitter CEO Jack Dorsey announced the launch of Bitcoin Academy, which will offer free classes on cryptocurrency for …",
                        "https://gizmodo.com/jay-z-jack-dorsey-bitcoin-academy-1849040407",
                        "https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/48fbb5e09973afbe730474c5cb0aadd4.jpg",
                        "2022-06-09T19:45:00Z",
                        "Rapper and entrepreneur Shawn Carter, better known as Jay-Z, is bringing Bitcoin to the place where he grew up. On Thursday, Jay-Z and former Twitter CEO Jack Dorsey announced the launch of Bitcoin A… [+2920 chars]"
                    )
                )
            )
            )
}