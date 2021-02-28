import React, { useState,useEffect, useContext } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';
import HomeScreen from '../HomeScreen';
import { AppContext } from '../../App';

export default function HomeTabNavigator(props){

    const {parentNavigation,navigation} = props;
    const context = useContext(AppContext);

    const Tab = createMaterialTopTabNavigator();

    const getLanguajeFilters = function(){
      return '?language='+context.user.learning[0].toLowerCase()+','+context.user.learning[0].toLowerCase();
    }

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
            {props =><HomeScreen navigation={navigation} parentNavigation={parentNavigation} filters={''}/>}
          </Tab.Screen>
          <Tab.Screen name="Learn">
            {props =><HomeScreen navigation={navigation} parentNavigation={parentNavigation} filters={getLanguajeFilters()}/>}
          </Tab.Screen>
        </Tab.Navigator>
    </NavigationContainer>
    )
}
