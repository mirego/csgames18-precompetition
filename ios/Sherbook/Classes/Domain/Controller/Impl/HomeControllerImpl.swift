//
//  HomeControllerImpl.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit

class HomeControllerImpl: HomeController {
    func getPosts(completion: @escaping (_ posts: [PostViewModel]?, _ error: Error?) -> ()) {
        FeedService.getPosts { (posts, error) in
            guard let posts = posts else {
                completion(nil, error)
                return
            }

            let postViewModels: [PostViewModel] = posts.map {
                HomeControllerImpl.buildPostViewModel(post: $0)
            }
            completion(postViewModels, nil)
        }
    }

    private static func buildPostViewModel(post: Post) -> PostViewModel {
        return PostViewModelImpl(post: post)
    }
}
