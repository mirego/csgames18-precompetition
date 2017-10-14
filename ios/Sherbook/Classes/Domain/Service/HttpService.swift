//
//  HttpService.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import Foundation

class HttpService {
    
    static public var token: String?

    static func get(url: String, completion: @escaping (_ data: Data?, _ error: Error?) -> ()) {
        guard let queryUrl = URL(string: url) else {
            completion(nil, NSError(domain: "HttpService", code: -1, userInfo: ["description" : "Invalid url"]))
            return
        }
        
        // construct the request
        var request = URLRequest(url: queryUrl)
        request.httpMethod = "GET"
        
        if let token = token {
            request.setValue(token, forHTTPHeaderField: "x-access-token")
        }

        URLSession.shared.dataTask(with: request) { (data, urlResponse, error) in
            DispatchQueue.main.async { completion(data, error) }
        }.resume()
    }
    
    static func post(url: String, data: String, completion: @escaping (_ data: Data?, _ error: Error?) -> ()) {
        guard let queryUrl = URL(string: url) else {
            completion(nil, NSError(domain: "HttpService", code: -1, userInfo: ["description" : "Invalid url"]))
            return
        }
        
        // construct the request
        var request = URLRequest(url: queryUrl)
        request.httpMethod = "POST"
        request.httpBody = data.data(using: .utf8)
        
        if let token = token {
            request.setValue(token, forHTTPHeaderField: "x-access-token")
        }
        
        URLSession.shared.dataTask(with: request) { (data, urlResponse, error) in
            DispatchQueue.main.async {
                completion(data, error)
            }
        }.resume()
    }
}
