import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

export default class Header extends React.Component {
  render() {
    return (
      <View style={styles.header}>
        {this.props.children}
      </View>
    );
  }
}

const styles = StyleSheet.create({
  header: {
    flex: 1,
    padding: 10,
    paddingTop: 30,
    backgroundColor: 'powderblue'
  },
});
