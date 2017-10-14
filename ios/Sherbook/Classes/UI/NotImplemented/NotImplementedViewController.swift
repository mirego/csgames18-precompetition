//
//  NotImplementedViewController.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit

class NotImplementedViewController: BaseViewController {

    private var mainView: NotImplementedView {
        return self.view as! NotImplementedView
    }

    init(title: String) {
        super.init(nibName: nil, bundle: nil)
        self.title = title
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = NotImplementedView()
    }
}

