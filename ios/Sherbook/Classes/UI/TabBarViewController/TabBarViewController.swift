//
//  TabBarViewController.swift
//  XMedius
//
//  Created by Hugo Lefrancois on 2017-01-20.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit

class TabBarViewController: UIViewController {

    private let tabsViewControllers: [UIViewController]

    var bottomInset: CGFloat {
        return mainView.bottomInset
    }

    var hiddenBottomHeight: CGFloat {
        return mainView.hiddenBottomHeight
    }

    fileprivate var currentTabIndex: Int = 0

    private var mainView: TabBarView {
        return self.view as! TabBarView
    }

    private var isSettingMainView = false

    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }

    init(tabsViewControllers: [UIViewController]) {
        self.tabsViewControllers = tabsViewControllers

        super.init(nibName: nil, bundle: nil)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = TabBarView()
        mainView.delegate = self
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureControllerAtIndex(0, firstConfiguration: true, animated: false)
        mainView.setSelectedTab(index: 0)
    }

    func configureControllerAtIndex(_ index: Int, firstConfiguration: Bool = false, animated: Bool, completion: (() -> (Void))? = nil) {
        let oldViewController = firstConfiguration ? nil : tabsViewControllers[currentTabIndex]
        oldViewController?.willMove(toParentViewController: nil)

        currentTabIndex = index
        let newViewController = tabsViewControllers[index]
        addChildViewController(newViewController)

        mainView.setMainView(newViewController.view, animationType: .none) {
            newViewController.didMove(toParentViewController: self)
            oldViewController?.removeFromParentViewController()
            completion?()
        }
    }

    func transitionToTabIndex(_ index: Int, animated: Bool, completion: (() -> (Void))? = nil) {
        guard index < tabsViewControllers.count, !isSettingMainView else { return }

        isSettingMainView = true

        mainView.setSelectedTab(index: index)
        if currentTabIndex != index {
            configureControllerAtIndex(index, animated: animated, completion: { [weak self] () -> (Void) in
                self?.isSettingMainView = false
                completion?()
            })
        } else {
            isSettingMainView = false
            completion?()
        }
    }
}

extension TabBarViewController: TabBarViewDelegate {
    func didTapPostButton() {
        let alertController = UIAlertController(title: "", message: "Not implemented", preferredStyle: .alert)
        alertController.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
        present(alertController, animated: true, completion: nil)
    }

    func didSelectTab(atIndex index: Int) {
        transitionToTabIndex(index, animated: true)
    }
}

extension UIViewController {
    var tabBarViewController: TabBarViewController? {
        var currentViewController: UIViewController? = self
        while currentViewController != nil {
            if let tabBarViewController = currentViewController as? TabBarViewController {
                return tabBarViewController
            }
            currentViewController = currentViewController?.parent
        }
        return nil
    }
}
