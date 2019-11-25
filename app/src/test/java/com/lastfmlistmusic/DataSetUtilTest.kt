package com.lastfmlistmusic

import com.lastfmlistmusic.view.createDrawDataList
import com.lastfmlistmusic.view.getExtremumList
import org.junit.Assert.assertEquals
import org.junit.Test

class DataSetUtilTest {

    @Test
    fun getExtremumListTest() {
        val height = 248
        val list = getExtremumList(height)

        println(list)

        assertEquals(0, list.last())
        assertEquals(1, list.get(list.indexOf(list.last()) - 1))
    }

    @Test
    fun createDrawDataListTest() {
        val height = 160
        val width = 328

        val list = createDrawDataList(width, height, false)

        list.forEach {println(it)}
    }

    @Test
    fun createDrawDataListInverseTest() {
        val height = 160
        val width = 328

        val list = createDrawDataList(width, height, true)
        val list2 = createDrawDataList(width, height, false)

        list.forEach {println(it)}
        println("false:")
        list2.forEach{println(it)}

        var result = true
        if (list.size >= 10 && list2.size >= 10) {
            for(i in 1 until 10) {
                println("list: ${list[i].startX - list[i - 1].startX}   list2: ${list2[i - 1].startX - list2[i].startX}")
                if((list[i].startX - list[i - 1].startX) != (list2[i - 1].startX - list2[i].startX)) {
                    result = false
                    break
                }
            }
        }

        assertEquals(true, result)
    }
}