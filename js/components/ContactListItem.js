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
        <Image style={styles.profilePic} source={{uri:this.state.contact.profilePicUrl}}/>
        <View style={styles.textContainer}>
          <Text style={styles.fullName}>{this.state.contact.firstName} {this.state.contact.lastName}</Text>
        </View>
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
    elevation: 4
  },
  fullName: {
    paddingLeft: 10,
    marginBottom: 5,
    fontSize: 20,
    fontWeight: 'bold',
    color: 'black'
  },
  profilePic: {
    width:75,
    height:75
  },
  textContainer: {
    paddingLeft: 20,
    flexDirection: 'column',
    justifyContent: 'center'
  }
});