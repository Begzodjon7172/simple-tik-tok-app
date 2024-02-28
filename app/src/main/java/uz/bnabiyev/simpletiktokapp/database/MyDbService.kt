package uz.bnabiyev.simpletiktokapp.database

import uz.bnabiyev.simpletiktokapp.models.MyReels

interface MyDbService {
    fun addReels(myReels: MyReels)
    fun getAllReels(): ArrayList<MyReels>
}