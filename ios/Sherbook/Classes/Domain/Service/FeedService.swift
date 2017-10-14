//
//  FeedService.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
// https://s3.amazonaws.com/shared.ws.mirego.com/competition/

import UIKit

class FeedService {
    private static var dev = "http://localhost:3000/"
    private static var prod = "http://130.211.216.25:3000/"
    private static let postsUrl = dev + "api/posts/"
    private static let createEP = "create"
    

    static func getPosts(completion: @escaping (_ posts: [Post]?, _ error: Error?) -> ()) {
        HttpService.get(url: postsUrl) { (data, error) in
            guard let data = data else {
                completion(nil, error)
                return
            }
             do {
                let feed = try JSONDecoder().decode(Feed.self, from: data)
                completion(feed.posts, error)
             } catch {
                print("Error trying to convert data to JSON")
                completion(nil, NSError(domain: "FeedService", code: -1, userInfo: ["description" : "Invalid data"]))
             }
        }
    }
    
    static func composePost(text: String, author: String, completion: @escaping (_ posts: [Post]?, _ error: Error?) -> ()) {
        let data = "author="+author+"&message="+text
        HttpService.post(url: postsUrl + createEP, data: data) { (data, error) in
            guard let data = data else {
                completion(nil, error)
                return
            }
            do {
                let feed = try JSONDecoder().decode(Feed.self, from: data)
                completion(feed.posts, error)
            } catch {
                print("Error trying to convert data to JSON : \(NSString(data: data, encoding: String.Encoding.utf8.rawValue))")
                completion(nil, NSError(domain: "FeedService", code: -1, userInfo: ["description" : "Invalid data : \(data)"]))
            }
        }
    }
}
