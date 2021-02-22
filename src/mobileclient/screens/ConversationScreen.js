import React, { useState,useEffect } from 'react';
import { GiftedChat } from 'react-native-gifted-chat';
import { StyleSheet,SafeAreaView } from 'react-native';

export default function RoomScreen(props) {

    const {parentNavigation} = props;

    useEffect(()=>{
        parentNavigation.setOptions({
            tabBarVisible: false
        });

        return () => {
            parentNavigation.setOptions({
                tabBarVisible: true
            });
        }
    },[])
  
  const [messages, setMessages] = useState([
    /**
     * Mock message data
     */
    // example of system message
    {
      _id: 0,
      text: 'New room created.',
      createdAt: new Date().getTime(),
      system: true
    },
    // example of chat message
    {
      _id: 1,
      text: 'Hello!',
      createdAt: new Date().getTime(),
      user: {
        _id: 2,
        name: 'Test User'
      }
    }
  ]);

  // helper method that is sends a message
  function handleSend(newMessage = []) {
    setMessages(GiftedChat.append(messages, newMessage));
  }

  return (
    <SafeAreaView style={ styles.container }>
      <GiftedChat
        messages={messages}
        onSend={newMessage => handleSend(newMessage)}
        user={{ _id: 1 }}
      />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1b2836',
    justifyContent: 'center'
  }
});
