import React, { useState,useEffect } from 'react';
import { View, Text} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';
import MessagesScreen from '../MessagesScreen';

export default function SearchTabNavigator({navigation}){

    const Tab = createMaterialTopTabNavigator();

    return(
    <NavigationContainer independent  initialRouteName="Results">
        <Tab.Navigator 
        tabBarOptions={{
          activeTintColor: '#1DA1F2',
          inactiveTintColor: '#435060',
          labelStyle: { 
            fontSize: 12            
          },
          tabStyle: { 
            height: 50
          },
          style: { 
            backgroundColor: '#1b2836',
          },
        }} >
          <Tab.Screen name="Results" component={MessagesScreen} />
          <Tab.Screen name="For you" component={MessagesScreen} />
        </Tab.Navigator>
    </NavigationContainer>
    )
}
