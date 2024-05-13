import os
import argparse
import collections
import re

def parse_line(line):
    match = re.match(r'(.*) (\d+), (.*) (\d+)', line.strip())
    if match:
        team1, score1, team2, score2 = match.groups()
        return team1, int(score1), team2, int(score2)
    return None, None, None, None

def update_points(team_points, team1, score1, team2, score2):
    if score1 is None or score2 is None:
        print(f"Scores for {team1} or {team2} are missing.")
        return
    if score1 > score2:
        team_points[team1] += 3
        team_points[team2] += 0
    elif score1 < score2:
        team_points[team2] += 3
        team_points[team1] += 0
    else:
        team_points[team1] += 1
        team_points[team2] += 1

def process_file(filename):
    if not os.path.exists(filename) or not os.access(filename, os.R_OK):
        print(f"File {filename} does not exist or is not readable.")
        return collections.defaultdict(int)
    team_points = collections.defaultdict(int)
    with open(filename, 'r') as file:
        for line in file:
            try:
                team1, score1, team2, score2 = parse_line(line)
            except ValueError:
                print(f"Line {line.strip()} is in the wrong format.")
                continue
            if team1 is not None:
                update_points(team_points, team1, score1, team2, score2)
    return team_points

def print_points(team_points):
    for i, (team, points) in enumerate(sorted(team_points.items(), key=lambda x: (-x[1], x[0])), start=1):
        print(f'{i}. {team}, {points} pts')

def main():
    parser = argparse.ArgumentParser(description="Process a file of soccer game results.")
    parser.add_argument('filename', metavar='filename', type=str, help='the name of the file to process')
    args = parser.parse_args()
    team_points = process_file(args.filename)
    print_points(team_points)

if __name__ == "__main__":
    main()