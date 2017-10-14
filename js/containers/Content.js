import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

export default class Content extends React.Component {
  render() {
    return (
      <View style={styles.content}>
        {this.props.children}
      </View>
    );
  }
}

const styles = StyleSheet.create({
  content: {
    flex: 3,
    padding: 20,
    backgroundColor: 'grey'
  },
});
