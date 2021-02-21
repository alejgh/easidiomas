import * as React from 'react';
import { Text, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Ionicons from 'react-native-vector-icons/Ionicons'
import HomeScreen from '../HomeScreen';
import MessagesStackNavigator from './MessagesStackNavigator';
import SearchStackNavigation from './SearchStackNavigation';
import SearchScreen from '../SearchScreen';




const Tab = createBottomTabNavigator();

  function SettingsScreen() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Text>Settings!</Text>
      </View>
    );
  }
  

export  function TabMenuNavigator({ navigation }) {
    return (
    <NavigationContainer independent>
        <Tab.Navigator screenOptions={({ route }) => ({
          tabBarIcon: ({ focused, color, size }) => {
            let iconName;

            if (route.name === 'Home') {
              iconName = focused ? 'home' : 'home-outline';
            } else if (route.name === 'Search') {
              iconName = focused ? 'search' : 'search-outline';
            }
            else if (route.name === 'Mesagges') {
                iconName = focused ? 'mail' : 'mail-outline';
            }
            else if (route.name === 'Profile') {
                iconName = focused ? 'person' : 'person-outline';
            }

            // You can return any component that you like here!
            return <Ionicons name={iconName} size={size} color={color} />;
          },
        })}
        animationEnabled
        tabBarOptions={{
          showIcon: true,
          showLabel:false,
          activeBackgroundColor: 'rgb(27, 42, 51)',
          inactiveBackgroundColor: 'rgb(27, 42, 51)',
          iconStyle:{
            marginBottom:5,
            marginTop:5
          },
          tabStyle: {
              justifyContent: 'center',
              alignItems: 'center'
          },
          indicatorStyle: {
            backgroundColor: 'transparent',
          },
          style: {
            borderTopWidth: 1,
            borderTopColor: '#435060'
          },
        }}
      >
          <Tab.Screen name="Home" component={HomeScreen} />
          <Tab.Screen name="Search" component={SearchScreen} />
          <Tab.Screen name="Mesagges" component={MessagesStackNavigator} />
          <Tab.Screen name="Profile" component={HomeScreen} />
        </Tab.Navigator>
    </NavigationContainer>
    );
  }
  