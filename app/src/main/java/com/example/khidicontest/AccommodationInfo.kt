package com.example.khidicontest

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.khidicontest.adapter.StayListAdapter
import com.example.khidicontest.adapter.TourListAdapter
import com.example.khidicontest.data.PlaceSource
import com.example.khidicontest.data.StaySource
import com.example.khidicontest.databinding.ActivityAccommodationInfoBinding
import com.example.khidicontest.model.Place
import com.example.khidicontest.model.Stay
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

var testT = ""
class AccommodationInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accommodation_info)

        val row = "20"
        val os = "AND"
        val mobile = "MobileApp"
        val area = "1"
        val type = "json"
        val key = "mXRRctbxiiVGHc2RoHWLY566OMFdj+ZUpFWjj0QV8JOxcb34YNUxRQnDdTN208sM+ABYCSaxZsWAXdveE4tEzQ=="
        val url = "https://apis.data.go.kr/B551011/KorService1/"

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val api:RetroInterface = retrofit.create(RetroInterface::class.java)

        val callGetStay = api.getStayData(row, os, mobile, type, area, key)

        callGetStay.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                val jsonObject: JSONObject = JSONObject(response.body())

                val res: JSONObject = jsonObject.getJSONObject("response")
                val resBody: JSONObject = res.getJSONObject("body")
                val bodyItems: JSONObject = resBody.getJSONObject("items")

                val jsonArray: JSONArray = bodyItems.getJSONArray("item")
                testT += "숙박정보 \n"
                for(i: Int in 0..jsonArray.length() - 1) {
                    val newOb = jsonArray.getJSONObject(i)
                    testT += "상호명: ${newOb.getString("title")} \n"
                    testT += "주소: ${newOb.getString("addr1")} \n"
                    testT += "전화번호: ${newOb.getString("tel")} \n\n"
//                    StaySource.stay.add(
//                        Stay(
//                            newOb.getString("title"),
//                            newOb.getString("addr1"),
//                            newOb.getString("tel")
//                        )
//                    )
                }


            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, "$t")
            }
        })

        val callGetTour = api.getTourData(row, os, mobile, type, area, key)

        callGetTour.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                val jsonObject: JSONObject = JSONObject(response.body())

                val res: JSONObject = jsonObject.getJSONObject("response")
                val resBody: JSONObject = res.getJSONObject("body")
                val bodyItems: JSONObject = resBody.getJSONObject("items")

                val jsonArray: JSONArray = bodyItems.getJSONArray("item")
                testT += "\n\n\n관광정보 \n"
                for(i: Int in 0..jsonArray.length() - 1) {
                    val newOb = jsonArray.getJSONObject(i)
                    testT += "상호명: ${newOb.getString("title")} \n"
                    testT += "주소: ${newOb.getString("addr1")} \n"
                    testT += "전화번호: ${newOb.getString("tel")} \n\n"
//                    PlaceSource.places.add(
//                        Place(
//                            R.drawable.ic_info,
//                            newOb.getString("title"),
//                            newOb.getString("addr1"),
//                            newOb.getString("tel")
//                        )
//                    )
                }
            }


            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, "$t")
            }
        })

        val texts: TextView = findViewById(R.id.place_tel)
        texts.text = "1"
        texts.text = testT

//        binding.stayListContainer.adapter = StayListAdapter(
//            applicationContext
//        )
//
//        binding.placeListContainer.adapter = TourListAdapter(
//            applicationContext
//        )
    }
}

interface RetroInterface {
    @GET("searchStay1?")
    fun getStayData(
        @Query("numOfRows") row: String?,
        @Query("MobileOS") os: String?,
        @Query("MobileApp") mobile: String?,
        @Query("_type") type: String?,
        @Query("areaCode") area: String?,
        @Query("serviceKey") key: String?
    ): Call<String>

    @GET("areaBasedList1?")
    fun getTourData(
        @Query("numOfRows") row: String?,
        @Query("MobileOS") os: String?,
        @Query("MobileApp") mobile: String?,
        @Query("_type") type: String?,
        @Query("areaCode") area: String?,
        @Query("serviceKey") key: String?
    ): Call<String>
}

//private fun Thread(url: String): Thread {
//    try {
//        val xml: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)
//
//        xml.documentElement.normalize()
//
//        val list:NodeList = xml.getElementsByTagName("item")
//
//        for(i in 0..list.length-1) {
//            val n: Node = list.item(i)
//
//            if(n.nodeType == Node.ELEMENT_NODE) {
//                val elem = n as Element
//                val map = mutableMapOf<String, String>()
//
//                for(j in 0..elem.attributes.length - 1) {
//                    map.putIfAbsent(elem.attributes.item(j).nodeName, elem.attributes.item(j).nodeValue)
//
//                }
//
//                println("======${i + 1}")
//                text += "======${i + 1}"
//                println("1. 주소 : ${elem.getElementsByTagName("addr1").item(0).textContent}")
//                text += "1. 주소 : ${elem.getElementsByTagName("addr1").item(0).textContent} \n"
//                println("2. 동 : ${elem.getElementsByTagName("addr2").item(0).textContent}")
//                text += "2. 동 : ${elem.getElementsByTagName("addr2").item(0).textContent} \n"
//                println("3. 상호명 : ${elem.getElementsByTagName("title").item(0).textContent}")
//                text += "3. 업소명 : ${elem.getElementsByTagName("title").item(0).textContent} \n"
//                println("4. 전화번호 : ${elem.getElementsByTagName("tel").item(0).textContent}")
//                text += "4. 전화번호 : ${elem.getElementsByTagName("tel").item(0).textContent} \n\n"
//            }
//        }
//    } catch (e: Exception) {
//        Log.d("APIError", e.toString())
//    }
//    val a: Thread = Thread()
//    return a
//}