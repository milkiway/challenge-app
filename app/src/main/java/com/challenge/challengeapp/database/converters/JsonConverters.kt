package com.challenge.challengeapp.database.converters

import androidx.room.TypeConverter
import com.challenge.challengeapp.database.entities.Address
import com.challenge.challengeapp.database.entities.Company
import com.google.gson.Gson

class JsonConverters {
    @TypeConverter
    fun addressToString(address: Address): String = Gson().toJson(address)

    @TypeConverter
    fun stringToAddress(string: String): Address = Gson().fromJson(string, Address::class.java)

    @TypeConverter
    fun companyToString(company: Company): String = Gson().toJson(company)

    @TypeConverter
    fun stringToCompany(string: String): Company = Gson().fromJson(string, Company::class.java)
}