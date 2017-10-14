import React from 'react';
import { StyleSheet, Text, View, Image, Dimensions } from 'react-native';

export default class ContactListItem extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      contact: props.contact
    };
  }

  render() {
    return (
      <View style={styles.contactContainer}>
        <Text style={styles.lastName}>{this.state.contact.lastName},</Text>
        <Text style={styles.firstName}>{this.state.contact.firstName}</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  contactContainer: {
    flexDirection: 'row',
    marginBottom: 8,
    padding: 10,
    backgroundColor: 'white',
  },
  firstName: {
    paddingLeft: 10,
    marginBottom: 5,
    fontWeight: 'bold',
    color: 'black'
  },
  lastName: {
    paddingLeft: 10,
    marginBottom: 5,
    fontWeight: 'bold',
    color: 'black'
  }
});