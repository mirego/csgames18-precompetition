//
//  HttpService.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import Foundation

class HttpService {

    static func get(url: String, completion: @escaping (_ data: Data?, _ error: Error?) -> ()) {
        guard let queryUrl = URL(string: url) else {
            completion(nil, NSError(domain: "HttpService", code: -1, userInfo: ["description" : "Invalid url"]))
            return
        }

        URLSession.shared.dataTask(with: queryUrl) { (data, urlResponse, error) in
            DispatchQueue.main.async { completion(data, error) }
        }.resume()
    }
}
