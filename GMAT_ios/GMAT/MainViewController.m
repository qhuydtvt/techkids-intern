//
//  MainViewController.m
//  GMAT
//
//  Created by Trung Đức on 4/6/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "MainViewController.h"
#import "MMDrawerBarButtonItem.h"
#import "UIViewController+MMDrawerController.h"
#import "Constant.h"
#import "QuestionPackCell.h"
#import "GmatAPI.h"
#import "QuestionPack.h"
#import "QuestionID.h"
#import "MagicalRecord.h"
#import "Question.h"
#import "QuestionViewController.h"

@interface MainViewController()

@property (nonatomic, assign) BOOL updated;

@end

@implementation MainViewController

- (void)viewDidLoad {
    
    [super viewDidLoad];
    
    self.title = kTitle_MainView;
    
    _updated = NO;
    if(_questionPacks.count == 0){
        [self loadQuestionPack];
    }
    
    //[self loadQuestions];
    
    // [self setupLeftMenuButton]
    
    [self configTableView];
    
}

#pragma mark - TableView Datasource

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _questionPacks.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *cellId = @"QeustionPackCell";
    
    QuestionPackCell *cell = [tableView dequeueReusableCellWithIdentifier:cellId];
    
    if (!cell) {
        NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"QuestionPackCell" owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    QuestionPack *quetionPack = _questionPacks[indexPath.row];
    
    cell.lblDate.text = quetionPack.available_time;
    cell.progessView.tintColor = kAppColor;
    cell.progessView.progress = 0.2;
    
    return cell;
    
}

#pragma mark - TableView Delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:NO];
    
    if ([Question MR_countOfEntities] != 0 || _updated == YES) {
        
        QuestionViewController *questionViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"question"];
        
        CATransition* transition = [CATransition animation];
        transition.duration = 0.3f;
        transition.type = kCATransitionMoveIn;
        transition.subtype = kCATransitionFromTop;
        [self.navigationController.view.layer addAnimation:transition
                                                    forKey:kCATransition];
        
        questionViewController.questions = [[NSMutableArray alloc]init];
        
        NSSortDescriptor *nameDescriptor = [NSSortDescriptor sortDescriptorWithKey:@"questionID" ascending:YES];
        QuestionPack *selectedQuestionPack = _questionPacks[indexPath.row];
        
        NSArray *listID = [[selectedQuestionPack.questionIDs allObjects] sortedArrayUsingDescriptors:[NSArray arrayWithObject:nameDescriptor]];
        
        NSLog(@"So cau hoi %lu", [Question MR_countOfEntities]);
        
        for (QuestionID *questionId in  listID) {
            Question *question = [Question MR_findFirstByAttribute:@"questionId" withValue: questionId.questionID];
            [questionViewController.questions addObject:question];
            
        }
        
        [self.navigationController pushViewController:questionViewController animated:NO];
        
    }
}

#pragma mark - Class funtions

+ (MainViewController *)sharedInstance;
{
    static MainViewController *sharedInstance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
        sharedInstance = [storyboard instantiateViewControllerWithIdentifier:@"mainView"];
    });
    return sharedInstance;
}

- (void)setupLeftMenuButton{
    MMDrawerBarButtonItem * leftDrawerButton = [[MMDrawerBarButtonItem alloc] initWithTarget:self action:@selector(leftDrawerButtonPress:)];
    [self.navigationItem setLeftBarButtonItem:leftDrawerButton animated:YES];
}

- (void)leftDrawerButtonPress:(id)sender{
    [self.mm_drawerController toggleDrawerSide:MMDrawerSideLeft animated:YES completion:nil];
}

- (void)configTableView;
{
    //    UIImageView *backgroundView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:kImage_TableQuestionPacksBackground]];
    //    [backgroundView setFrame:self.tbvQuestionPacks.frame];
    //
    //    self.tbvQuestionPacks.backgroundView = backgroundView;
    
    self.tbvQuestionPacks.separatorColor = kAppColor;
    
    self.tbvQuestionPacks.tableFooterView = [[UIView alloc]init];
    
    self.tbvQuestionPacks.rowHeight = 90.0f;
}

- (void)loadQuestionPacks;
{
    //    [sGmatAPI exploreQuestionPackWithCompletionBlock:^(NSArray *questionPacks) {
    //
    //        for (NSDictionary *jsonDict in questionPacks) {
    //            QuestionPack *newQuestionPack = [[QuestionPack alloc]initWithJson:jsonDict];
    //            [_questionPacks addObject:newQuestionPack];
    //        }
    //        [_tbvQuestionPacks reloadData];
    //    }];
    [_tbvQuestionPacks reloadData];
    
}
-(void)loadQuestionPack;{
    UIActivityIndicatorView *loadingView = [[UIActivityIndicatorView alloc]initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
    [self.tbvQuestionPacks addSubview:loadingView];
    loadingView.center = self.view.center;
    [loadingView startAnimating];
    [sGmatAPI exploreQuestionPacksWithCompletionBlock:^(NSArray *questionPacks) {
        NSArray *questionPascks = [QuestionPack MR_findAllSortedBy:@"packID" ascending:YES];
        NSLog(@"COUNT PACK: %ld", questionPacks.count);
        _questionPacks = [[NSMutableArray alloc]initWithArray:questionPascks];
        [loadingView stopAnimating];
        [_tbvQuestionPacks reloadData];
    }];
}

- (void)loadQuestions;
{
    [sGmatAPI exploreQuestionWithCompletionBlock:^(NSArray *question) {
        _updated = YES;
    }];
}

@end
