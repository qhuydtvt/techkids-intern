//
//  HomeViewController.m
//  GMAT
//
//  Created by Do Ngoc Trinh on 5/19/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "HomeViewController.h"
#import "MainViewController.h"
#import "AboutViewController.h"
#import "QuestionPack.h"
#import "MagicalRecord.h"
#import "Constant.h"

#import "GmatAPI.h"

@interface HomeViewController ()

@end

@implementation HomeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = @"Home";
    [self customButton];
    // Do any additional setup after loading the view.
}
-(void)viewWillAppear:(BOOL)animated{
    
    self.navigationController.navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName:kColor_Window};
    self.navigationController.navigationBar.translucent = NO;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

+ (HomeViewController *)sharedInstance;
{
    static HomeViewController *sharedInstance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
        sharedInstance = [storyboard instantiateViewControllerWithIdentifier:@"HomeViewController"];
    });
    return sharedInstance;
}

#pragma mark - Button Action
-(IBAction)btnQuesionPackDidTap:(id)sender;{
    
    __weak typeof(self) weakSelf = self;
    [self setAnimationForButton:_btnQuestionPack withCompletion:^{
        MainViewController *mainView = [MainViewController sharedInstance];
        
        NSArray *questionPacks = [QuestionPack MR_findAllSortedBy:@"packID" ascending:YES];
        NSLog(@"PACK COUNT: %ld", questionPacks.count);
        mainView.questionPacks = [[NSMutableArray alloc]initWithArray:questionPacks];
        [weakSelf gotoViewController:mainView];
    }];
}

-(IBAction)btnSummaryDidTap:(id)sender;{
    __weak typeof(self) weakSelf = self;
    [self setAnimationForButton:_btnSummary withCompletion:^{
        [weakSelf showAlertWithMessage:@"Chua co view"];
    }];
}

-(IBAction)btnAbout:(id)sender;{
    
    __weak typeof(self) weakSelf = self;
    [self setAnimationForButton:_btnAbout withCompletion:^{
        AboutViewController *aboutView = [weakSelf.storyboard instantiateViewControllerWithIdentifier:@"AboutViewController"];
        [weakSelf gotoViewController:aboutView];
        
    }];
}

-(IBAction)btnREviewDidTap:(id)sender;{
    __weak typeof(self) weakSelf = self;
    [self setAnimationForButton:_btnREview withCompletion:^{
        [weakSelf showAlertWithMessage:@"Chua co view"];
    }];
}

#pragma mark - Function
-(void)gotoViewController:(UIViewController*)viewController;{
    __weak typeof(self) weakSelf = self;
    [weakSelf.navigationController pushViewController:viewController animated:YES];
}
-(void)showAlertWithMessage:(NSString*)message;{
    UIAlertView *alert = [[UIAlertView alloc]initWithTitle:message message:@"" delegate:self cancelButtonTitle:@"OK" otherButtonTitles: nil];
    [self.view addSubview:alert];
    [alert show];
}

-(void)setAnimationForButton:(UIButton*)button withCompletion:(void(^)(void))completion;{
    [UIView animateWithDuration:0.1 animations:^{
        button.transform = CGAffineTransformMakeScale(1.1, 1.1);
    } completion:^(BOOL finished) {
        [UIView animateWithDuration:0.1 animations:^{
            button.transform = CGAffineTransformMakeScale(1, 1);
        } completion:^(BOOL finished) {
            completion();
        }];
    }];
}
-(void)customButton;{
    
    float roundF = _btnSummary.frame.size.width/4;
    _btnQuestionPack.layer.cornerRadius = roundF;
    _btnQuestionPack.clipsToBounds = YES;
    _btnSummary.layer.cornerRadius = roundF;
    _btnSummary.clipsToBounds = YES;
    _btnREview.layer.cornerRadius = roundF;
    _btnREview.clipsToBounds = YES;
    _btnAbout.layer.cornerRadius = roundF;
    _btnAbout.clipsToBounds = YES;
    
    _btnQuestionPack.backgroundColor = kColor_NavigationBarBackground;
    _btnSummary.backgroundColor = kColor_NavigationBarBackground;
    _btnREview.backgroundColor = kColor_NavigationBarBackground;
    _btnAbout.backgroundColor = kColor_NavigationBarBackground;
    
    self.view.backgroundColor = kColor_background;
}

@end
