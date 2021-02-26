import React, { useState,useEffect, useContext } from 'react';
import {AppContext} from '../../App';
import { GiftedChat } from 'react-native-gifted-chat';
import { StyleSheet,SafeAreaView } from 'react-native';

export default function RoomScreen(props) {

    const context = useContext(AppContext);
    const {parentNavigation} = props;
    const {user} = props.route.params;
 
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


    const getMessages = async function(){
      let response = await (await fetch('http://localhost:5000/api/mock/chats/messages')).json();
      let newMessages = [];
      for(let msg in response){
        let sender = await getUser(response[msg].sender)
        if(sender.username == context.user.username){
          newMessages.push({
            _id: response[msg].id,
            text: response[msg].text,
            createdAt: new Date(response[msg].createdAt).getTime(),
            user: {
              _id: 1
            }
          })
        }else{
          newMessages.push({
            _id: response[msg].id,
            text: response[msg].text,
            createdAt: new Date(response[msg].createdAt).getTime(),
            user: {
              _id: 2,
              name: user.name,
              avatar: user.avatar
            }
          })
        }
        
      }
      console.log(newMessages)
      setMessages(newMessages.reverse());
    }

    const getUser = async function(url){
      return (await fetch('http://localhost:5000/'+url)).json();
    }

    useEffect(()=>{
        parentNavigation.setOptions({
            tabBarVisible: false
        });


        getMessages();

        return () => {
            parentNavigation.setOptions({
                tabBarVisible: true
            });
        }
    },[])
  
  

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
