import React from 'react';
import { StyleSheet, Text, View, Image, Dimensions } from 'react-native';
import AutoHeightImage from 'react-native-auto-height-image';

export default class Post extends React.Component {
    constructor(props){
        super(props);

        this.state = {
            author : props.author,
            message: props.message,
            attachment : props.attachment
        };
    }

    render() {
        const showImage = this.state.attachment !== null && this.state.attachment.type === 'image';
        const screenWidth = Dimensions.get('window').width;

        return (
            <View style={styles.postContainer}>
                <Text style={styles.title}>{this.state.author}</Text>
                <Text style={styles.message}>{this.state.message}</Text>
                { showImage ? <AutoHeightImage style={styles.image} width={screenWidth} imageURL={this.state.attachment.url} /> : <Text></Text>}
            </View>
        );
    }
}

const styles = StyleSheet.create({
    postContainer : {
        marginBottom:8,
        paddingTop: 5,
        backgroundColor: 'white',
        elevation: 4
    },
    title: {
        paddingLeft: 10,
        marginBottom: 5,
        fontWeight: 'bold',
        color: 'black'
    },
    message: {
        paddingLeft: 10,
        paddingRight: 10,
        textAlign: 'justify',
        marginBottom: 5
    },
    image : {
        flex:1,
        alignSelf: 'center',
    }
});
