import React, { useEffect } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import LoginScreen from '../auth/LoginScreen';
import SignUpScreen from '../auth/SignUpScreen';
import SignUpProfileScreen from '../auth/SignUpProfileScreen';

export default function AuthStackNavigator(){

    const Stack = createStackNavigator();

    return(
    <NavigationContainer independent  
    transitionerStyle={{backgroundColor: '#222'}}>
        <Stack.Navigator 
         screenOptions={{
            animationEnabled: true, //poner a false para evitar flasazo
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
            
            <Stack.Screen name="Login" component={LoginScreen} options={{
                 headerShown: false
            }}/>
            <Stack.Screen name="Sign Up" component={SignUpScreen}/>
            <Stack.Screen name="Sign Up Profile" component={SignUpProfileScreen}/>
            
        </Stack.Navigator>
    </NavigationContainer>
    )
}