import React, { useState,useEffect } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';
import HomeScreen from '../HomeScreen';

export default function HomeTabNavigator(props){

    const {parentNavigation,navigation} = props;

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
          <Tab.Screen name="Posts">
            {props =><HomeScreen navigation={navigation} parentNavigation={parentNavigation}/>}
          </Tab.Screen>
          <Tab.Screen name="Learn">
            {props =><HomeScreen navigation={navigation} parentNavigation={parentNavigation}/>}
          </Tab.Screen>
        </Tab.Navigator>
    </NavigationContainer>
    )
}
