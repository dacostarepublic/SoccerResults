package main

import (
	"bufio"
	"fmt"
	"os"
	"regexp"
	"sort"
	"strconv"
	"strings"
)

var teamPoints = make(map[string]int)

func main() {
	if len(os.Args) < 2 {
		fmt.Println("Please provide a filename as an argument.")
		os.Exit(1)
	}

	filename := os.Args[1]
	processFile(filename)
	printPoints()
}

func processFile(filename string) {
	file, err := os.Open(filename)
	if err != nil {
		fmt.Println("File", filename, "does not exist or is not readable.")
		return
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		parts := parseLine(line)
		if parts[0] != "" {
			score1, _ := strconv.Atoi(parts[1])
			score2, _ := strconv.Atoi(parts[3])
			updatePoints(parts[0], score1, parts[2], score2)
		}
	}
}

func parseLine(line string) []string {
	re := regexp.MustCompile(`(.*?) (\d+), (.*?) (\d+)`)
	matches := re.FindStringSubmatch(strings.TrimSpace(line))
	if len(matches) == 5 {
		return matches[1:]
	}
	return []string{"", "", "", ""}
}

func updatePoints(team1 string, score1 int, team2 string, score2 int) {
	if score1 > score2 {
		teamPoints[team1] += 3
		teamPoints[team2] += 0
	} else if score1 < score2 {
		teamPoints[team1] += 0
		teamPoints[team2] += 3
	} else {
		teamPoints[team1]++
		teamPoints[team2]++
	}
}

func printPoints() {
	type Team struct {
		Name   string
		Points int
	}

	var teams []Team

	for name, points := range teamPoints {
		teams = append(teams, Team{name, points})
	}

	sort.Slice(teams, func(i, j int) bool {
		if teams[i].Points == teams[j].Points {
			return teams[i].Name < teams[j].Name
		}
		return teams[i].Points > teams[j].Points
	})

	for index, team := range teams {
		fmt.Printf("%d. %s, %d pts\n", index+1, team.Name, team.Points)
	}
}
