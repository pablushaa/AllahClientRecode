package ru.pablusha.AllahRecode.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public class JsonUtil {
	public static Gson json = new Gson();
	public static Gson prettyJson = new GsonBuilder().setPrettyPrinting().create();
	public static JsonParser parser = new JsonParser();
}