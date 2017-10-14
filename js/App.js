import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Header from './containers/Header';
import Content from './containers/Content';
import Feed from './components/Feed';
import NavMenu from './components/NavMenu';

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
