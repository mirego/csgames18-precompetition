//
//  FeedService.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit

class FeedService {
    private static let baseUrl = "https://s3.amazonaws.com/shared.ws.mirego.com/competition/"
    private static let feedPath = "sherbook.json"

    static func getPosts(completion: @escaping (_ posts: [Post]?, _ error: Error?) -> ()) {
        HttpService.get(url: baseUrl + feedPath) { (data, error) in
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
}
