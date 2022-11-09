package com.muzz.muzzdemo.utility;

import android.annotation.SuppressLint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muzz.muzzdemo.R;
import com.muzz.muzzdemo.model.MessageModel;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {

    private final List<MessageModel> messages;

    public static View.OnClickListener onItemClickListener;

    public MessageListAdapter(List<MessageModel> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageListAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListAdapter.MessageViewHolder holder, int position) {
        holder.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.message_timestamp)
        TextView messageTimestamp;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.sent_message)
        FrameLayout sentMessage;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.sent_message_text)
        TextView sentMessageText;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.sent_message_tick)
        ImageView sentMessageTick;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.received_message)
        FrameLayout receivedMessage;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.received_message_text)
        TextView receivedMessageText;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }

        void bind(MessageModel message) {
            //Convert unix timestamp to the desired timestamp
            messageTimestamp.setText(Html.fromHtml(TimeStamp.getTimeStamp(message.getUnix())));

            if (Objects.equals(message.getUserID(), "Zaid K. Al Qassar")) {
                sentMessageText.setText(message.getMessageText());
                receivedMessage.setVisibility(View.GONE);
            } else {
                receivedMessageText.setText(message.getMessageText());
                sentMessage.setVisibility(View.GONE);
            }

            if (message.isReceived()) {
                sentMessageTick.setImageResource(R.drawable.ic_message_received);
            } else {
                sentMessageTick.setImageResource(R.drawable.ic_message_ticked);
            }
        }
    }
}
