//
//  HomeView.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit

class HomeView: UIView {

    private let layout = UICollectionViewFlowLayout()
    private let collectionView: UICollectionView
    private var postViewModels: [PostViewModel] = []

    private let cellToDetermineHeight = PostViewCell(frame: .zero)

    init() {
        collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        super.init(frame: .zero)

        collectionView.backgroundColor = .silver
        collectionView.register(PostViewCell.self, forCellWithReuseIdentifier: PostViewCell.reuseIdentifier)
        collectionView.dataSource = self
        collectionView.delegate = self
        collectionView.contentInset = .top(9)
        addSubview(collectionView)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        collectionView.size = size
    }

    func configure(postViewModels: [PostViewModel]) {
        self.postViewModels = postViewModels
        collectionView.reloadData()
    }

    func setBottomInset(_ bottomInset: CGFloat, hiddenBottomHeight: CGFloat) {
        collectionView.contentInset = UIEdgeInsets(top: collectionView.contentInset.top, left: collectionView.contentInset.left, bottom: bottomInset, right: collectionView.contentInset.right)
        collectionView.scrollIndicatorInsets = UIEdgeInsets(top: collectionView.scrollIndicatorInsets.top, left: collectionView.scrollIndicatorInsets.left, bottom: hiddenBottomHeight, right: collectionView.scrollIndicatorInsets.right)
    }
}

extension HomeView: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return postViewModels.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: PostViewCell.reuseIdentifier, for: indexPath) as! PostViewCell
        cell.configure(postViewModel: postViewModels[indexPath.row])
        return cell
    }
}

extension HomeView: UICollectionViewDelegateFlowLayout, UICollectionViewDelegate
{
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: width, height: cellToDetermineHeight.heightForWidth(width, postViewModel: postViewModels[indexPath.row]))
    }
}
