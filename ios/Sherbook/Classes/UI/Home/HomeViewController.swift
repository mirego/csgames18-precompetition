//
//  HomeViewController.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit

class HomeViewController: BaseViewController {

    private var mainView: HomeView {
        return self.view as! HomeView
    }

    private let homeController: HomeController

    init(homeController: HomeController) {
        self.homeController = homeController
        super.init(nibName: nil, bundle: nil)
        title = "Accueil"
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = HomeView()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        homeController.getPosts { [weak self] (posts, error) in
            if let posts = posts {
                self?.mainView.configure(postViewModels: posts)
            } else {
                if let error = error {
                    self?.displayError(message: error.localizedDescription)
                } else {
                    self?.displayError(message: "Unknown error")
                }
            }
        }
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        if let tabBarViewController = tabBarViewController {
            mainView.setBottomInset(tabBarViewController.bottomInset, hiddenBottomHeight: tabBarViewController.hiddenBottomHeight)
        }
    }

    private func displayError(message: String) {
        let alertController = UIAlertController(title: "Error", message: message, preferredStyle: .alert)
        alertController.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
        present(alertController, animated: true, completion: nil)
    }
}
