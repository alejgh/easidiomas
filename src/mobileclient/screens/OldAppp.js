import * as React from 'react';
import { createDrawerNavigator } from '@react-navigation/drawer';
import { NavigationContainer } from '@react-navigation/native';
import { CustomDrawerContent } from './screens/navigation/CustomDrawerContent'
import { TabMenuNavigator } from './screens/navigation/TabMenuNavigator';

const Drawer = createDrawerNavigator();

export default function App() {
  return (
      <NavigationContainer>
        <Drawer.Navigator drawerContent={(props) => <CustomDrawerContent {...props} />} initialRouteName="Profile">
          <Drawer.Screen name="Home" component={TabMenuNavigator} />
        </Drawer.Navigator>
      </NavigationContainer>
  );
}