package com.muzz.muzzdemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muzz.muzzdemo.model.MessageModel;
import com.muzz.muzzdemo.utility.Database;
import com.muzz.muzzdemo.utility.TimeStamp;

import java.util.ArrayList;
import java.util.List;

public class MessagingViewModel extends ViewModel {

    private final MutableLiveData<String> toastMessageObserver = new MutableLiveData<>();

    private MutableLiveData<List<MessageModel>> messagesMutableLiveData;

    private Database database;

    //Observing data changes to display toast message.
    public LiveData<String> getToastObserver() {
        return toastMessageObserver;
    }

    //Retrieving the message list data.
    public MutableLiveData<List<MessageModel>> getMessageList() {
        if (messagesMutableLiveData == null) {
            messagesMutableLiveData = new MutableLiveData<>();
        }
        return messagesMutableLiveData;
    }

    //Sending message data to be save in the local SQL database.
    public void sendMessage(String userID, String messageContent) {
        MessageModel message = new MessageModel(userID, messageContent, TimeStamp.generateTimeStamp(), 0);

        database.addDBMessage(message.getUserID(), message.getMessageText(), message.getUnix(), 0);

        List<MessageModel> messageList = new ArrayList<>();
        messageList.add(message);

        messagesMutableLiveData.setValue(messageList);
    }

    //Fetching messages from local SQL database
    public void fetchMessages() {
        messagesMutableLiveData.setValue(database.getDBMessages());
    }
}
