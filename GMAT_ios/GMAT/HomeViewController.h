//
//  HomeViewController.h
//  GMAT
//
//  Created by Do Ngoc Trinh on 5/19/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BaseViewController.h"

@interface HomeViewController : BaseViewController
+ (HomeViewController *)sharedInstance;
@property (weak, nonatomic) IBOutlet UIButton *btnQuestionPack;
@property (weak, nonatomic) IBOutlet UIButton *btnSummary;
@property (weak, nonatomic) IBOutlet UIButton *btnAbout;
@property (weak, nonatomic) IBOutlet UIButton *btnREview;
-(IBAction)btnQuesionPackDidTap:(id)sender;
-(IBAction)btnSummaryDidTap:(id)sender;
-(IBAction)btnAbout:(id)sender;
-(IBAction)btnREviewDidTap:(id)sender;
@end
