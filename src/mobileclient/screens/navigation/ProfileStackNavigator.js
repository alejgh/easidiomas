import React, { useEffect, useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import ProfileScreen from '../ProfileScreen';
import EditProfileScreen from '../profile/EditProfileScreen';


export default function ProfileStackNavigator({route,navigation}){

    const {user} = route.params;
    const Stack = createStackNavigator();


  /*
    {
     "id": 1,
     "name": "Pepito",
     "surname": "Sanchez Perez"
     "learning": [“en”, "ru"],
     "speaks": "es",
     "birthDate": "<long_time_since_epoch>",
     "avatar": "<url_del_avatar>"
    }
    */


    return(
    <NavigationContainer independent  initialRouteName='Profile'>
        <Stack.Navigator
         screenOptions={{
            headerShown: false
          }}>
            <Stack.Screen name='Profile'>
                {props => 
                <ProfileScreen {...props}
                     user={user} 
                     parentNavigation={navigation} />}
            </Stack.Screen>
            <Stack.Screen name='Edit Profile'>
                {props => <EditProfileScreen {...props} parentNavigation={navigation} />}
            </Stack.Screen>
        </Stack.Navigator>
    </NavigationContainer>
    )
}
