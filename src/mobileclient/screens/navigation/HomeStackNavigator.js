import React, { useState,useEffect } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from '../HomeScreen';

export default function HomeStackNavigator({navigation}){

    const Stack = createStackNavigator();

    return(
    <NavigationContainer independent  initialRouteName="Results">
        <Stack.Navigator  screenOptions={{
                headerStyle: {
                    backgroundColor: '#1b2836',
                    borderBottomWidth: 1,
                    borderBottomColor: '#435060'

                },
                headerTintColor: '#fff',
                headerTitleStyle: {
                    fontWeight: 'bold',
                }
            }}>   
          <Stack.Screen name="Home" component={HomeScreen} />
        </Stack.Navigator>
    </NavigationContainer>
    )
}
