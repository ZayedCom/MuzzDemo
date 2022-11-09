package com.muzz.muzzdemo.utility;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muzz.muzzdemo.R;
import com.muzz.muzzdemo.model.MessageModel;

import java.util.List;

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
        @BindView(R.id.message_text)
        TextView messageText;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.message_tick)
        ImageView messageTick;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }

        void bind(MessageModel message) {
            //Convert unix timestamp to the desired timestamp
            messageTimestamp.setText(TimeStamp.getTimeStamp(message.getUnix()));
            messageText.setText(message.getMessageText());

            if (message.isReceived()) {
                messageTick.setImageResource(R.drawable.ic_message_received);
            } else {
                messageTick.setImageResource(R.drawable.ic_message_ticked);
            }
        }
    }
}
