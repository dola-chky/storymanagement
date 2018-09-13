package com.assignment.storyManagement.Utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateDeserializer extends JsonDeserializer<Date>{
    private SimpleDateFormat dateFormat = new SimpleDateFormat(
            "dd-MM-yyyy");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String str = jsonParser.getText().trim();
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            System.out.println("can not deserialize date");
        }
        return deserializationContext.parseDate(str);
    }
}
