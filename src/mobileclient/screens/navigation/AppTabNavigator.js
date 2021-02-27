import * as React from 'react';
import { Text, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Ionicons from 'react-native-vector-icons/Ionicons'
import ChatsStackNavigator from './ChatsStackNavigator';
import DiscoverStackNavigator from './DiscoverStackNavigator';
import ProfileStackNavigator from './ProfileStackNavigator';
import HomeStackNavigator from './HomeStackNavigator';


const Tab = createBottomTabNavigator();

export const AppNavigationRef = React.createRef();
  
export default function AppTabNavigator({ navigation }) {
    return (
    <NavigationContainer independent >
        <Tab.Navigator initialRouteName="Home" ref={AppNavigationRef} screenOptions={({ route }) => ({
          tabBarIcon: ({ focused, color, size }) => {
            let iconName;

            if (route.name === 'Home') {
              iconName = focused ? 'home' : 'home-outline';
            } else if (route.name === 'Discover') {
              iconName = focused ? 'search' : 'search-outline';
            }
            else if (route.name === 'Chats') {
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
          activeBackgroundColor: '#1b2836',
          inactiveBackgroundColor: '#1b2836',
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
          <Tab.Screen name="Home" component={HomeStackNavigator} />
          <Tab.Screen name="Discover" component={DiscoverStackNavigator} />
          <Tab.Screen name="Chats" component={ChatsStackNavigator} />
          <Tab.Screen name="Profile" component={ProfileStackNavigator}  listeners={({ navigation, route }) => ({
              tabPress: e => {
                navigation.navigate('Profile',{user:DefaultUser,isOwner:true});
              },
            })}/>
        </Tab.Navigator>
    </NavigationContainer>
    );
  }


  
export const DefaultUser = {
  id: 99,
  name: 'Pablo',
  surname: 'Menéndez Suárez',
  username: '@mistermboy',
  learning: ['en', 'cn'],
  speaks: 'es',
  birthDate: '<long_time_since_epoch>',
  avatar: 'https://bootdey.com/img/Content/avatar/avatar2.png'
}
  