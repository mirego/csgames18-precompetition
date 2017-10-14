import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import NavList from './NavList';

export default class NavMenu extends React.Component {
  render() {
    return (
      <View style={{flex: 1}}>
        <View style={styles.currentPage}>
          <Text>Home</Text>
        </View>
        <View style={styles.navBar}>
          <NavList />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  currentPage: {
    flex: 3,
  },
  navBar: {
    flex: 1,
  }
});
