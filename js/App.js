import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Header from './Header';
import Content from './Content';
import Feed from './Feed';
import NavMenu from './NavMenu';

export default class App extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <Header>
          <NavMenu />
        </Header>
        <Content>
          <Feed />
        </Content>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1
  },
});
