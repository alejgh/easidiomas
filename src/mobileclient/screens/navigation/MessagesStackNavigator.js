import React, { useEffect } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { HeaderBackButton } from '@react-navigation/stack';
import MessagesScreen from '../MessagesScreen';
import ConversationScreen from '../ConversationScreen';

export default function MessagesStackNavigator({navigation}){

    const Stack = createStackNavigator();

    return(
    <NavigationContainer independent  initialRouteName="Messages">
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
            <Stack.Screen name="Messages">
                {props => <MessagesScreen {...props} parentNavigation={navigation} />}
            </Stack.Screen>
            <Stack.Screen name="Conversation">
                {props => <ConversationScreen {...props} parentNavigation={navigation} />}
            </Stack.Screen>
        </Stack.Navigator>
    </NavigationContainer>
    )
}