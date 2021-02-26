import React, { useEffect } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import LoginScreen from '../auth/LoginScreen';
import SignUpScreen from '../auth/SignUpScreen';
import AppTabNavigator from '../navigation/AppTabNavigator';

export default function AuthStackNavigator(){

    const Stack = createStackNavigator();

    return(
    <NavigationContainer independent  initialRouteName="Messages"
    transitionerStyle={{backgroundColor: '#222'}}>
        <Stack.Navigator 
         screenOptions={{
            headerShown: false,
            animationEnabled: true, //poner a false para evitar flasazos
          }}>
            <Stack.Screen name="Login" component={LoginScreen}/>
            <Stack.Screen name="SignUp" component={SignUpScreen}/>
        </Stack.Navigator>
    </NavigationContainer>
    )
}