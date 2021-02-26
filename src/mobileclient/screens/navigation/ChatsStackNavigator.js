import React, { useEffect, useRef } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import RoomScreen from '../chat/RoomScreen';
import ChatsScreen from '../ChatsScreen';

export const ChatNavigationRef = React.createRef();

export default function ChatsStackNavigator({route,navigation}){

    const Stack = createStackNavigator();
    const chatNavigation = useRef(ChatNavigationRef);
        
    useEffect(()=>{
        if(route?.params?.startChat){
            chatNavigation?.current?.current?.navigate('Room',{user:route.params.user});
        }
    })


    return(
    <NavigationContainer independent  initialRouteName="Chats" ref={ChatNavigationRef}>
        <Stack.Navigator
         screenOptions={{
            headerTintColor: '#fff',
            headerTitleStyle: {
                fontWeight: 'bold',
            },
            headerStyle: { 
                backgroundColor: '#1b2836',
                borderBottomColor: '#435060',
                borderBottomWidth:1
            },
          }}>
            <Stack.Screen name="Chats">
                {props => <ChatsScreen {...props} parentNavigation={navigation} />}
            </Stack.Screen>
            <Stack.Screen name="Room">
                {props => <RoomScreen {...props} parentNavigation={navigation} />}
            </Stack.Screen>
        </Stack.Navigator>
    </NavigationContainer>
    )
}